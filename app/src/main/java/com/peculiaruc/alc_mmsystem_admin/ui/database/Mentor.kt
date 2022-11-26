package com.peculiaruc.alc_mmsystem_admin.ui.database

import android.provider.ContactsContract.CommonDataKinds.Organization

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
