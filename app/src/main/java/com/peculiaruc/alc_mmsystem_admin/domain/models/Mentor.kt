package com.peculiaruc.alc_mmsystem_admin.domain.models

/**
* defines the Mentor object
* received from the database
*/

data class Mentor(
    var name: String,
    var role: String,
    var organization: String?,
    var pronouns: String?,
    var profileImageUrl: String?,
    var email: String,
    var country: String,
    var bio: String,
    var proficiency: String,
    var yearsOfExperience: Int,
    var websiteUrl: String?,
    var githubUrl: String?,
    var linkedInUrl: String?,
    var instagramUrl: String?,
    var twitterUrl: String?,
)


/**
 *  represent the Mentor object that will come from api .
 */
data class Mentor2(
    val name: String,
    val image: String,
    val role: String,
    val tags: List<String>
)