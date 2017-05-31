package utils

/**
  * Created by jyothi on 30/5/17.
  */
import models.UserInfo
import pdi.jwt.{JwtAlgorithm, JwtJson}
import play.api.libs.json.Json

object Helpers {

  object Auth {

    val secret = "Shhhhh.. It's top secret..!" //take from conf or something else

    val algorithm = JwtAlgorithm.HS256 //we can use any supported algorithm here

    def generateToken(userInfo: UserInfo) = { //here userInfo is claim
      val claim = Json.toJson(userInfo).toString //stringify since can't encode JsValue
      val token = JwtJson.encode(claim, secret, algorithm)
      token
    }

    def userInfoFromToken(token: String) = {
      JwtJson.decodeJson(token, secret, Seq(JwtAlgorithm.HS256)) //Success[JsObject]
    }

  }

}
