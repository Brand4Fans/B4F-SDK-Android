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
import org.joda.time.DateTime


/**
 * 
 * @param name User name
 * @param surname User surname
 * @param email User email
 * @param birthday User birthday
 * @param tutorName 
 * @param tutorSurname 
 * @param tutorEmail 
 */
data class User (

    /* User name */
    @field:Json(name = "name")val name: kotlin.String? = null,
    /* User surname */
    @field:Json(name = "surname")val surname: kotlin.String? = null,
    /* User email */
    @field:Json(name = "email")val email: kotlin.String? = null,
    /* User birthday */
    @field:Json(name = "birthday")val birthday: DateTime? = null,
    @field:Json(name = "tutorName")val tutorName: kotlin.String? = null,
    @field:Json(name = "tutorSurname")val tutorSurname: kotlin.String? = null,
    @field:Json(name = "tutorEmail")val tutorEmail: kotlin.String? = null
) {
}