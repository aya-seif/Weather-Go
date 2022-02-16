package com.app.weatheappworld.remote

import com.google.gson.annotations.SerializedName

data class AlertsItem(

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("sender_name")
	val senderName: String? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("event")
	val event: String? = null
)