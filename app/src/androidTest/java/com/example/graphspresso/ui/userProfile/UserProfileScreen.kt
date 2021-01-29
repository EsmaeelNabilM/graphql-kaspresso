package com.example.graphspresso.ui.userProfile

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.text.KTextView
import com.example.graphspresso.R
import com.kaspersky.kaspresso.screens.KScreen

class UserProfileScreen() : KScreen<UserProfileScreen>() {
    override val layoutId: Int = R.layout.user_profile_fragment
    override val viewClass: Class<*> = UserProfileFragment::class.java

    val avatar = KImageView { withId(R.id.avatar) }
    val name = KTextView { withId(R.id.name) }
    val bio = KTextView { withId(R.id.bio) }
    val repos = KTextView { withId(R.id.repos) }
    val reposCount = KTextView { withId(R.id.reposCount) }

}