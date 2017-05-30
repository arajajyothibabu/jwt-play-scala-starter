package models

import play.api.libs.json.Json

/**
  * Created by jyothi on 30/5/17.
  */
case class UserLoginRequestModel(username: String, password: String)

object UserLoginRequestModel {
  implicit val userLoginRequestModelFormat = Json.format[UserLoginRequestModel]
}
