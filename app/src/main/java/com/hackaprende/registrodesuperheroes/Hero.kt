package com.hackaprende.registrodesuperheroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Hero(val name: String, val alterEgo: String, val bio: String, val power: Float) : Parcelable