package models

import play.api.libs.json.Json

/**
  * Created by jyothi on 31/5/17.
  */
case class UserInfo(userId: String, someBla: String) //we can add anything to this

object UserInfo {
  implicit val userInfoFormat = Json.format[UserInfo]
}
