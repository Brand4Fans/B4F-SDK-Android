package com.batura.b4fLibrary.rest

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Secure
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.batura.b4fLibrary.Either
import com.batura.b4fLibrary.api.EitherCallAdapterFactory
import com.batura.b4fLibrary.rest.adapters.CampaignTypeAdapter
import com.batura.b4fLibrary.rest.adapters.CouponTypeAdapter
import com.batura.b4fLibrary.rest.adapters.DateTimeAdapter
import com.batura.b4fLibrary.rest.error.B4FError
import com.batura.b4fLibrary.rest.models.*
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import retrofit2.http.Body
import java.io.IOException

internal val moshi = Moshi.Builder()
        .add(DateTimeAdapter())
        .add(CouponTypeAdapter())
        .add(CampaignTypeAdapter())
        .build()


internal class RetrofitRest(private val applicationContext: Context) {

    private val mainKey = MasterKey.Builder(applicationContext)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val sharedPreferences = EncryptedSharedPreferences.create(applicationContext,
        AUTH_PREF_FILE,mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

   @SuppressLint("HardwareIds")
   private fun getIdPhone() : String{
       return Secure.getString(applicationContext.contentResolver,
               Secure.ANDROID_ID)
   }
    private val moshiAdapter = moshi.adapter(BindRequest::class.java)

    internal var userRequest : BindRequest = BindRequest()
        private set(value) {
        sharedPreferences
                .edit()
                .putString(AUTHORIZED_USER,
                        moshiAdapter.toJson( value.copy(deviceOS = "Android")))
                .commit()
        field = value
    }
    get() {
        val dataString = sharedPreferences.getString(AUTHORIZED_USER,"")
        return if (dataString.isNullOrBlank()){
            return BindRequest()
        }else{
            try {
                moshiAdapter.fromJson(dataString)!!
            }catch (_ : IOException){
                return BindRequest()
            }

        }
    }

    internal var accessToken : String ? = null
        set(value){
        sharedPreferences.edit().putString(ACCESS_TOKEN,value).commit()
        field = value
    }
    get() {
        return sharedPreferences.getString(ACCESS_TOKEN,null)
    }

    internal var apiKey : String =""
        set(value){
            sharedPreferences.edit().putString(API_KEY,value).commit()
            field = value
        }
        get() {
            return sharedPreferences.getString(API_KEY,"")!!
        }


    private val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(HeaderInterceptor(this))
            .build()

    private val  retrofit = Retrofit.Builder()
        .baseUrl("https://apidev.brand4fans.com/api/")
            .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(EitherCallAdapterFactory())
        .build()


    fun bind() : Deferred<Either<B4FError, Bind>> {
        val coroutineScope = CoroutineScope(Dispatchers.IO)

        return coroutineScope.async {
            val result =  getAccess().bind(userRequest)

            if (result.isRight){
                accessToken = result.getRight()!!.accessToken!!
            }

            result
        }
    }

    private fun getAccess() : Access{
        return retrofit.create(Access::class.java)
    }

    fun getCampaign() : Campaign{
        return retrofit.create(Campaign::class.java)
    }

    fun getCoupon() : Coupon{
        return retrofit.create(Coupon::class.java)
    }

    fun getNews() : News{
        return retrofit.create(News::class.java)
    }

    fun getSmartTag() : SmartTag{
        return retrofit.create(SmartTag::class.java)
    }

    fun getUserProfile() : UserProfile{
        return retrofit.create(UserProfile::class.java)
    }

    fun getAlerts(): Alert {
        return retrofit.create(Alert::class.java)
    }

    fun setDeviceToken(deviceToken: String) {
        userRequest = userRequest.copy(tokenPush = deviceToken,idDevice = getIdPhone())
    }

    fun setUserData(userID: String, deviceToken: String?) {
        userRequest = userRequest.copy(tokenPush = deviceToken?: "",
                originUserHash = userID,
                idDevice = getIdPhone())
    }

    fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    interface Access{
        @POST("access/vinculation")
        suspend fun bind(@Body dtoBindRequest: BindRequest) : Either<B4FError, Bind>
    }

    interface Alert{
        @GET("alert/")
        suspend fun getAlerts(@Query("skip") skip: Int,
                                   @Query("take") take: Int):
                Either<B4FError, AlertList>

        @GET("alert/countNotRead")
        suspend fun setAlertRead():
                Either<B4FError, AlertCountNotRead>

        @PUT("alert/{id}/read")
        suspend fun setAlertRead(@Path("id") idAlert: String):
                Either<B4FError, Unit>


        @PUT("alert/readAll")
        suspend fun setAllAlertRead():
                Either<B4FError, Unit>
    }

    interface Campaign {
        @GET("campaign/my")
        suspend fun getMyCampaigns(@Query("skip") skip: Int,
                                 @Query("take") take: Int,
                                 @Query("filterCampaignType")filterCampaignType : List<String>,
                                 @Query("filterCompany")filterCompany : List<String>,
                                 @Query("filterBadge")filterBadge : List<String>):
                Either<B4FError, CampaignList>

        @GET("campaign/available")
        suspend fun getCampaignAvailable(@Query("skip") skip: Int,
                                  @Query("take") take: Int,
                                  @Query("filterCampaignType")filterCampaignType : List<String>,
                                  @Query("filterCompany")filterCompany : List<String>,
                                  @Query("filterBadge")filterBadge : List<String>):
                Either<B4FError, CampaignList>

        @GET("campaign/filter")
        suspend fun getCampaignFilter()
        : Either<B4FError, CampaignFilter>

        @GET("campaign/{id}")
        suspend fun getCampaignBy(@Path("id") campaignId: String)
        : Either<B4FError,
                CampaignDetail>

        @POST("campaign/{id}/subscribe")
        suspend fun subscribeInACampaign(@Path("id") campaignId: String,
                              @Body dtoSubscribeRequest: Subscribe)
        : Either<B4FError, Unit>

        @POST("campaign/{id}/linkAndSubscribe")
        suspend fun linkAndSubscribe(@Path("id") campaignId: String,
                                     @Body subscribe: LinkAndSubscribe)
        : Either<B4FError, Unit>




        @GET("/campaign/avaiable")
        suspend fun getCampaignFilter(@Query("skip") skip: Int,
                                      @Query("take") take: Int,
                                      @Query("filterCampaignType")filterCampaignType : List<String>,
                                      @Query("filterCompany")filterCompany : List<String>,
                                      @Query("filterBadge")filterBadge : List<String>)
        : Either<B4FError, CampaignList>



    }
    interface Coupon{

        @GET("coupon/to-use")
        suspend fun getCouponsToUse(@Query("skip") skip: Int,
                                   @Query("take") take: Int,
                                   @Query("filterCampaignType")filterCampaignType : List<String>,
                                   @Query("filterCompany")filterCompany : List<String>,
                                   @Query("filterBadge")filterBadge : List<String>):
                Either<B4FError, CouponListItem>

        @GET("coupon/unavailable")
        suspend fun getCouponUnavailable(@Query("skip") skip: Int,
                                   @Query("take") take: Int,
                                   @Query("filterCampaignType")filterCampaignType : List<String>,
                                   @Query("filterCompany")filterCompany : List<String>,
                                   @Query("filterBadge")filterBadge : List<String>):
                Either<B4FError, CouponListItem>


        @GET("coupon/filter")
        suspend fun getCouponFilter()
                : Either<B4FError, CouponFilter>


        @GET("coupon/{id}")
        suspend fun getCouponBy(@Path("id") couponId: String)
                : Either<B4FError,
                CouponDetail>


        @PUT("coupon/{id}/redeem")
        suspend fun setCouponAsRedeem(@Path("id") couponId: String)
                : Either<B4FError,
                Unit>


        @DELETE("coupon/{id}/unsubscribe")
        suspend fun unsubscribeCouponFromCampaign(@Path("id") couponId: String)
                : Either<B4FError,
                Unit>
    }
    interface News {
        @GET("news")
        suspend fun getNews(@Query("skip") skip: Int, @Query("take") take: Int) : Either<B4FError, NewsList>

        @GET("news/{id}")
        suspend fun getNewBy(@Path("id") campaignId: String) : Either<B4FError, NewsDetail>
    }
    interface SmartTag{
        @GET("smart-tag/")
        suspend fun getSmartTag(@Query("skip") skip: Int, @Query("take") take: Int) : Either<B4FError, SmartTagList>
        @GET("smart-tag/{id}")
        suspend fun getUserSmartTag(@Path("id") smartTagId: String) : Either<B4FError, SmartTagDetail>
        @POST("smart-tag/")
        suspend fun associateSmartTag(@Body linkAndSubscribe: Link) : Either<B4FError, Unit>
        @HTTP(method = "DELETE", path = "smart-tag/", hasBody = true)
        suspend fun disassociateSmartTag(@Body dtoUnlinkRequest: Unlink) : Either<B4FError, Unit>
    }


    interface UserProfile{
        @GET("user-profile")
        suspend fun getUserProfile() : Either<B4FError, User>
        @PUT("user-profile")
        suspend fun updateUserProfile(@Body dtoUpdateRequest: UpdateRequest) : Either<B4FError, Unit>
        @DELETE("user-profile")
        suspend fun deleteUserProfile() : Either<B4FError, Unit>
    }

    companion object{
        private const val AUTH_PREF_FILE = "Auth"
        private const val AUTHORIZED_USER = "AuthorizedUser"
        private const val ACCESS_TOKEN = "TokenUser"
        private const val API_KEY = "ApiKey"
    }
}


