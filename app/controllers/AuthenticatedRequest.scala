package controllers

import models.UserInfo
import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import utils.Helpers._

/**
  * Created by jyothi on 31/5/17.
  */
class AuthenticatedRequest[A](val user: UserInfo, request: Request[A]) extends WrappedRequest[A](request)

object AuthenticatedAction extends ActionBuilder[AuthenticatedRequest] {

  def invokeBlock[A](request: Request[A], block: (AuthenticatedRequest[A]) => Future[Result]) = {
    val userInfoFromToken = Auth.userInfoFromToken(request.headers.apply("WWW-Authenticate"))
    if(userInfoFromToken.isSuccess){
      val userInfoOpt = userInfoFromToken.get.validate[UserInfo].asOpt
      if(userInfoOpt.isDefined){
        block(new AuthenticatedRequest(userInfoOpt.get, request))
      }else{
        Future.successful(Results.BadRequest("BadRequest..!"))
      }
    }else{
      Future.successful(Results.Unauthorized("UnAuthorized..!"))
    }
  }

}
