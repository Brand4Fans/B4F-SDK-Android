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
 * @param avatar 
 * @param legal 
 */
data class ComplementList (

    @field:Json(name = "avatar")val avatar: ComplementListItem? = null,
    @field:Json(name = "legal")val legal: List<ComplementListItem>? = null
) {
}