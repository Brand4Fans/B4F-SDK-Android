/**
 * Third Party B4F Service
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package com.batura.b4fLibrary.rest.models

import com.squareup.moshi.Json


/**
 * 
 * @param valueIdI 
 * @param modelNameNv 
 * @param requiredB 
 * @param labelNv 
 */
data class LocationModel (

    @field:Json(name = "valueIdI")val valueIdI: kotlin.String? = null,
    @field:Json(name = "modelNameNv")val modelNameNv: kotlin.String? = null,
    @field:Json(name = "requiredB")val requiredB: kotlin.Boolean? = null,
    @field:Json(name = "labelNv")val labelNv: kotlin.String? = null
) {
}