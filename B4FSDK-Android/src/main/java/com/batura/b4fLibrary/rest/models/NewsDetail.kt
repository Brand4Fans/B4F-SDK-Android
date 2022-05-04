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
 * @param id News identifier
 * @param title News title
 * @param summary News summary
 * @param publishDate News publish date
 * @param endDate News end date
 * @param videoUrl News video url
 * @param html News html content
 * @param complements 
 * @param sponsors News list of sponsor
 * @param tags News list of tags
 * @param categories News list of categories
 */
data class NewsDetail (

    /* News identifier */
    @field:Json(name = "id")val id: kotlin.String? = null,
    /* News title */
    @field:Json(name = "title")val title: kotlin.String? = null,
    /* News summary */
    @field:Json(name = "summary")val summary: kotlin.String? = null,
    /* News publish date */
    @field:Json(name = "publishDate")val publishDate: DateTime? = null,
    /* News end date */
    @field:Json(name = "endDate")val endDate: DateTime? = null,
    /* News video url */
    @field:Json(name = "videoUrl")val videoUrl: kotlin.String? = null,
    /* News html content */
    @field:Json(name = "html")val html: kotlin.String? = null,
    @field:Json(name = "complements")val complements: ComplementList? = null,
    /* News list of sponsor */
    @field:Json(name = "sponsors")val sponsors: List<NewsSponsorListItem>? = null,
    /* News list of tags */
    @field:Json(name = "tags")val tags: List<TagListItem>? = null,
    /* News list of categories */
    @field:Json(name = "categories")val categories: List<CategoryListItem>? = null
) {
}