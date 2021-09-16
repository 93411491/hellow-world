package com.example.likedouyin.bean

/**
 *  author : wangzhirui
 *  date : 2021/9/7
 *  description : 视频实体类
 */
class ViewBean {
//    视频id
    var viewId = 0
//    视频资源
    var viewRes = 0
//    封面资源
    var coverRes = 0
//    视频文案
    var content:String? = null
        get() = if (field==null)"文案为空" else field
//    是否点赞
    var isLiked = false
//    发布距离现在时间
    var distanceTime = 0f
//    是否关注
    var isFocused = false
//    点赞量
    val likeCount = 0
//    评论数
    val commentCount = 0
//    分享数
    val shareCount = 0

    inner class UserBean{
//        用户id
        var userId = 0
//        用户名
        var userName:String ?=null
            get() = if (field==null)"用户名为空" else field
//        个人签名
        var personalSign: String? = null
            get() = if (field==null)"用户名为空" else field
//        是否被关注
        var isFocused = false
//        获赞数
        val praisedCount = 0
//        关注数
        val focusedCount = 0
//        粉丝数
        val fansCount = 0
//        作品数
        val workCount = 0;
//        动态数
        val dynamicCount =0
//        喜欢数
        val likeCount =0
    }
}