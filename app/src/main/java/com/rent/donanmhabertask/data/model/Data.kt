package com.rent.donanmhabertask.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Entity
data class Data (
	@ColumnInfo
	@SerializedName("Id")
	val id : Int,

	@ColumnInfo
	@SerializedName("VideoId") val videoId : Int,

	@ColumnInfo
	@SerializedName("Url") val url : String,

	@ColumnInfo
	@SerializedName("Image") val image : Image,

	@ColumnInfo
	@SerializedName("ImageCount") val imageCount : Int,

	@ColumnInfo
	@SerializedName("ColorAvarage") val colorAvarage : String,

	@ColumnInfo
	@SerializedName("TextColor") val textColor : String,

	@ColumnInfo
	@SerializedName("SubTextColor") val subTextColor : String,

	@ColumnInfo
	@SerializedName("EditorColor") val editorColor : String,

	@ColumnInfo
	@SerializedName("Title") val title : String,

	@ColumnInfo
	@SerializedName("DateWellFormed") val dateWellFormed : String,

	@ColumnInfo
	@SerializedName("CreateDate") val createDate : Int,

	@ColumnInfo
	@SerializedName("ThreadId") val threadId : Int,

	@ColumnInfo
	@SerializedName("TotalRead") val totalRead : Int,

	@ColumnInfo
	@SerializedName("OnlineUser") val onlineUser : Int,

	@ColumnInfo
	@SerializedName("ShortContent") val shortContent : String,

	@ColumnInfo
	@SerializedName("SourceUrl") val sourceUrl : String,

	@ColumnInfo
	@SerializedName("TotalComment") val totalComment : Int,

	@ColumnInfo
	@SerializedName("IsLocked") val isLocked : Int,



	@ColumnInfo
	@SerializedName("Duration") val duration : String,

	@ColumnInfo
	@SerializedName("IsFavorite") val isFavorite : Int,

	@ColumnInfo
	@SerializedName("FavoriteCount") val favoriteCount : Int,

	@ColumnInfo
	@SerializedName("ShareCount") val shareCount : Int,

	@ColumnInfo
	@SerializedName("CommentCount") val commentCount : Int,



	@ColumnInfo
	@SerializedName("Type") val type : Int,



	@ColumnInfo
	@SerializedName("GalleryId") val galleryId : Int,

	@ColumnInfo
	@SerializedName("GalleryTotalRead") val galleryTotalRead : Int,

	@ColumnInfo
	@SerializedName("SurveyId") val surveyId : Int,

	@ColumnInfo
	@SerializedName("EditorsChooseMessageId") val editorsChooseMessageId : Int,

	@ColumnInfo
	@SerializedName("TotalReputation") val totalReputation : Int,

	@ColumnInfo
	@SerializedName("MyReputation") val myReputation : Boolean
) {
	@PrimaryKey(autoGenerate = true)
	var uuid: Int = 0
}