package services

import models.{UserInfo, UserLoginRequestModel}
import utils.Helpers.Auth

/**
  * Created by jyothi on 30/5/17.
  */
class AuthService {

  val dummyDataSource = UserLoginRequestModel("arajajyothibabu", "password")

  def login(userLoginRequestModel: UserLoginRequestModel) = { //just for mock
    /**
      * Do the DB operation to check whether user exists and get necessary BlaBla details
      */
    val token = Auth.generateToken(UserInfo(userLoginRequestModel.username, "SomeBlaBla From DB"))
    (userLoginRequestModel.username == dummyDataSource.username && userLoginRequestModel.password == dummyDataSource.password, token)
  }


}
