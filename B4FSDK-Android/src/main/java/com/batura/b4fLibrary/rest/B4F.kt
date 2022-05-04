package com.batura.b4fLibrary.rest

import android.content.Context
import com.batura.b4fLibrary.rest.models.*
import com.batura.b4fLibrary.Either
import com.batura.b4fLibrary.rest.error.B4FError
import kotlinx.coroutines.*

class B4F private constructor(applicationContext: Context) {


    companion object {
        private var b4F : B4F? = null

        /**
         * Initialization
         */
        fun init(applicationContext: Context, apiKey: String){
            b4F = B4F(applicationContext)
            b4F!!.auth.setApiKey(apiKey)
        }

        /**
         *
         */
        fun single() : B4F{
            if (b4F != null){
                return b4F!!
            }else{
                throw ExceptionInInitializerError()
            }
        }
    }

    internal val retrofitRest =  RetrofitRest(applicationContext)

    val auth : Auth by lazy { Auth(this) }
    val campaign : Campaign by lazy { Campaign(retrofitRest.getCampaign())}
    val coupon : Coupon by lazy { Coupon(retrofitRest.getCoupon())}
    val news : News by lazy { News(retrofitRest.getNews()) }
    val alerts : Alerts by lazy { Alerts(retrofitRest.getAlerts())}
    val smartTag : SmartTag by lazy { SmartTag(retrofitRest.getSmartTag()) }
    val userProfile : UserProfile by lazy { UserProfile(retrofitRest.getUserProfile()) }

}

class Auth internal constructor(private val b4F: B4F) {

    /**
     * Set the application Api key to use
     *  @param apiKey the apiKey to use
     */
    fun setApiKey(apiKey : String){
        b4F.retrofitRest.setApiKey(apiKey)
    }

    /**
     * Set userData with you login with the B4F backend
     * @param userID your userId with you have authentificated with our servers
     * @param deviceToken push token to register, may be null
     */
    fun setUserData(userID : String,deviceToken: String? = null){
        b4F.retrofitRest.setUserData(userID,deviceToken)
        deviceToken?.let {
            b4F.retrofitRest.setDeviceToken(deviceToken)
        }

    }

    /**
     * When your push token need to be changed you need to call this function
     * @param deviceToken push token to register, not be null
     * @param callback result of the query, if you need it or null if you wan't need the response
     */
    fun refreshPushToken(deviceToken : String,callback: ((Either<B4FError, Bind>) -> Unit)?) {
        b4F.retrofitRest.setDeviceToken(deviceToken)
        @Suppress("DeferredResultUnused")
        GlobalScope.launch(Dispatchers.IO) {
            val referred =  b4F.retrofitRest.bind()

            val result = referred.await()
            callback?.invoke(result)
        }
    }
}

class Campaign internal constructor(internal val campaign: RetrofitRest.Campaign) {

    /**
     * Method to get the my campaigns to the user paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getMyCampaigns(skip: Int,
                      take: Int,
                      filterCampaignType : List<String> = listOf(),
                      filterCompany : List<String> = listOf(),
                      filterBadge : List<String> = listOf(),
                      callback : (Either<B4FError, CampaignList>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.getMyCampaigns(skip,take,filterCampaignType,filterCompany,filterBadge)
            callback(result)
        }
    }

    /**
     * Method to get the my campaigns available paginated. User register is not compulsory
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getAvailableCampaigns(skip: Int,
                              take: Int,
                              filterCampaignType : List<String> = listOf(),
                              filterCompany : List<String> = listOf(),
                              filterBadge : List<String> = listOf(),
                              callback : (Either<B4FError, CampaignList>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.getCampaignAvailable(skip,take,filterCampaignType,filterCompany,filterBadge)
            callback(result)
        }
    }

    /**
     * Method to get campaign list filters
     */
    fun getFilterCampaigns(callback : (Either<B4FError, CampaignFilter>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.getCampaignFilter()
            callback(result)
        }
    }

    /**
     * Method to get the detail of a campaign from a user
     * @param id Campaign identifier
     */
    fun getCampaignById(id: String, callback : (Either<B4FError, CampaignDetail>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.getCampaignBy(id)
            callback(result)
        }
    }

    /**
     * Method to subscribe to a campaign
     * @param id Identifier of the campaign to subscribe
     */
    fun subscribeToCampaignWithId(id: String, smartTagUserId : String, callback: (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.subscribeInACampaign(id,
                Subscribe(smartTagUserId)
            )
            callback(result)
        }
    }

    /**
     * Method to link a smarttag and subscribe to a campaign
     * @param id Identifier of the campaign to subscribe
     */
    fun linkAndSubscribeToCampaignWithId(id: String, smartTagUserId : String, callback: (Either<B4FError, Unit>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val result =  campaign.linkAndSubscribe(id,
                LinkAndSubscribe(smartTagUserId)
            )
            callback(result)
        }
    }
}

class Alerts internal constructor(internal val alert: RetrofitRest.Alert){

    /**
     * Method to get the alerts for the user paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getAlerts(skip: Int,
                        take: Int,
                        callback : (Either<B4FError, AlertList>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  alert.getAlerts(skip,take)
            callback(result)
        }
    }

    /**
     * Method to get number of alerts not read
     */
    fun getNotReadAlertCount(
                        callback : (Either<B4FError, AlertCountNotRead>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  alert.setAlertRead()
            callback(result)
        }
    }

    /**
     * Method to mark a notification pending to read as read
     * @param idAlert Identifier of the alert
     */
    fun setAlertReadById(idAlert: String,
                         callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  alert.setAlertRead(idAlert)
            callback(result)
        }
    }

    /**
     * Method to mark notifications pending to read as read
     */
    fun setAllAlertsRead(callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  alert.setAllAlertRead()
            callback(result)
        }
    }
}

class Coupon internal constructor(internal val coupon: RetrofitRest.Coupon){

    /**
     * Method to get the coupons available to the user paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getCoupons(skip: Int,
                   take: Int,
                   filterCampaignType : List<String> = listOf(),
                   filterCompany : List<String> = listOf(),
                   filterBadge : List<String> = listOf(),
                   callback : (Either<B4FError, CouponListItem>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.getCouponsToUse(skip,take,filterCampaignType,filterCompany,filterBadge)
            callback(result)
        }
    }
    /**
     * Method to get the coupons unavailable to the user paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getUnavailableCoupons(skip: Int,
                              take: Int,
                              filterCampaignType : List<String> = listOf(),
                              filterCompany : List<String> = listOf(),
                              filterBadge : List<String> = listOf(),
                              callback : (Either<B4FError, CouponListItem>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.getCouponUnavailable(skip,take,filterCampaignType,filterCompany,filterBadge)
            callback(result)
        }
    }
    /**
     * Method to get coupon list filters
     */
    fun getFiltersCoupon(callback : (Either<B4FError, CouponFilter>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.getCouponFilter()
            callback(result)
        }
    }

    /**
     * Method to get the detail of a coupon from a user
     *  @param couponId Coupon identifier
     */
    fun getCouponByID(couponId: String,callback : (Either<B4FError, CouponDetail>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.getCouponBy(couponId)
            callback(result)
        }
    }

    /**
     * Method to mark a coupon as used. Only available for coupon-type campaigns
     * @param couponId Identifier of the coupon to use
     */
    fun redeemCouponWithId(couponId: String,
                           callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.setCouponAsRedeem(couponId)
            callback(result)
        }
    }
    /**
     * Method to unsubscribe from a campaign
     * @param couponId Identifier of the coupon to unsubscribe
     */
    fun unsubscribeFromCampaignWithCouponId(couponId: String,
                                            callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  coupon.unsubscribeCouponFromCampaign(couponId)
            callback(result)
        }
    }
}

class News internal constructor(internal val news: RetrofitRest.News) {


    /**
     * Method to get the news available to the user paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getNews(skip: Int,
                take: Int,
                callback : (Either<B4FError, NewsList>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  news.getNews(skip,take)
            callback(result)
        }
    }

    /**
     * Method to get the detail of a news of a user
     * @param newsIdentifier News identifier
     */
    fun getNewById(newsIdentifier: String,
                   callback : (Either<B4FError, NewsDetail>) -> Unit){
        GlobalScope.launch(Dispatchers.IO) {
            val result =  news.getNewBy(newsIdentifier)
            callback(result)
        }
    }

}

class SmartTag internal constructor(internal val smartTag: RetrofitRest.SmartTag) {


    /**
     * Method to get the user smarttags paginated
     * @param skip Number of items to skip for pagination
     * @param take Number of items to take for pagination
     */
    fun getSmartTags(skip : Int, take : Int,callback : (Either<B4FError, SmartTagList>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result = smartTag.getSmartTag(skip,take)
            callback(result)
        }
    }

    /**
     * Method to associate a smarttag with a user
     * @param idSmartTag Smarttag identifier
     */
    fun associateSmartTag(idSmartTag: String,callback :  (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result = smartTag.associateSmartTag(Link(idSmartTag))
            callback(result)
        }
    }

    /**
     * Method to disassociate a smarttag with a user
     * @param idSmartTag smartTag ID
     */
    fun disassociateSmartTag(idSmartTag : String, callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result = smartTag.disassociateSmartTag(Unlink(idSmartTag))
            callback(result)
        }
    }

    /**
     * Method to get the detail of a user smarttag
     * @param idSmartTag Smarttag identifier
     */
    fun getSmartTagById(smartTagId : String,callback : (Either<B4FError, SmartTagDetail>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result = smartTag.getUserSmartTag(smartTagId)
            callback(result)
        }
    }


}

class UserProfile internal constructor(internal val userProfile: RetrofitRest.UserProfile) {

    /**
     * Method that returns the user's profile
     */
    fun getUser(callback : (Either<B4FError, User>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result =   userProfile.getUserProfile()
            callback(result)
        }
    }

    /**
     * Method that updates the user's profile
     * @param dtoUpdateRequest the user model updated
     */
    fun updateUser(dtoUpdateRequest : UpdateRequest, callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result =   userProfile.updateUserProfile(dtoUpdateRequest)
            callback(result)
        }
    }

    /**
     * Method to delete a user
     */
    fun deleteUser(callback : (Either<B4FError, Unit>) -> Unit){
        GlobalScope.launch(Dispatchers.IO){
            val result =   userProfile.deleteUserProfile()
            callback(result)
        }
    }

}
