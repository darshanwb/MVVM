package com.app.mvvm.model

data class NotificationResponse(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: ArrayList<NotificationData>
)

data class NotificationData(
    val _id: String,
    val isRead: Boolean,
    val type: String,
    val userId: String,
    val actorId: String,
    val postId: String,
    val isDeleted: Boolean,
    val createdAt: String,
    val postUserData: PostUserData,
    val actionUserData: PostUserData,
    val commentData: CommentData,
)

data class PostUserData(
    val _id: String,
    val name: String,
    val profilePic: String
)
data class CommentData(
    val _id: String,
    val comment: String,
)
