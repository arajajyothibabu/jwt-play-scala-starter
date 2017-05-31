# jwt-play-scala-starter

#### A starter web application powered by Play Framework, Scala and JWT.

##### You can secure your Controller actions by writing a Wrapper Request to authenticate the user with JWT token issued after login.

##### Use this sample Play App as a starter to Authenticate your Rest API calls.

- Thanks to [pauldijou/jwt-scala](https://github.com/pauldijou/jwt-scala) for scala implementation of JWT

#### Flow

- Generate token after login with user `claim`, `SomeSecretKey`, and Some Algorithm `(HS256, HMD5 ..)`

- `claim` may contain your `userInfo`

        val claim = Json.obj(
            user => yourUserId,
            role => userRealm,
            details => someDetails
        )
        
- So decoded token contains all our claim which is used in secured Actions 

        AuthenticatedRequest[A](
            user: UserInfo,
            request: Request[A]
        )

- Then every secured `Action` contains an `AuthenticatedRequest`

        def secured(params: AnyRef) = AuthenticatedAction { implicit request =>
            Ok("You are legitimate Mr. " + request.user.username)
        }
        
#### Advantages:
- No need to make costly DB operations for userInfo every time, since our decoded token have all those information.
- Secure your Rest API calls with token from Headers.
- And more you can figure out

#### Disadvantages
- Compromised Secret Key : we store the secret key in server, if some one cracks it ?
- Data Overhead : Based on the size of claim we keep