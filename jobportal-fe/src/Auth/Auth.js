import { useState } from "react";
import Signin from "./Signin";
import Signup from "./Signup";
import './Auth.scss'

function Auth(props) {

    const [authMode, setAuthMode] = useState("signin")

    const toggleAuthMode = () => {
        setAuthMode(authMode === "signin" ? "signup" : "signin")
    }

    return (
        <div className="Auth">
        {authMode === "signin" 
            ?
            <>
                <Signin setLoggedUser={props.setLoggedUser}/>
                <br/><br/>
                Not a user? <span className="link" onClick={toggleAuthMode}>SignUp </span>
            </>             
            : 
            <>
                <Signup/>
                <br/><br/>
                Already a user? <span className="link" onClick={toggleAuthMode}>SignIn </span>
            </>
            
        }
        </div>
    );
}
  
  export default Auth;
  