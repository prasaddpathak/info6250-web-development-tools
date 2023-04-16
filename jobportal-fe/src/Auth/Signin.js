import { useState } from "react";

function Signin(props) {

    const [uname, setUname] = useState("prasaddpathak@gmail.com")
    const [pass, setPass] = useState("test123")

    const handleUname = (e) => {
        setUname(e.target.value)
    }

    const handlePass = (e) => {
        setPass(e.target.value)
    }

    const handleSignin = async (e) => {
        e.preventDefault();
        const decodedToken = uname + ":" + pass
        const encodedToken = btoa(decodedToken)
        const basicToken ='Basic '+encodedToken
        const authResponse = await fetch('http://localhost:8080/v1/users/auth',
                    { headers: {Authorization : basicToken}})
        console.log(authResponse.status);

        if (authResponse.status == 400) {
            alert('Incorrect email/password')
            return
        }

        const authResponseJSON = await authResponse.json()
        if (authResponse.status == 200) {
            props.setLoggedUser(authResponseJSON)
        }
    }

    return (
        <div className="Login">
        <h1>Login</h1>
        <form>
            <br/>
            <label htmlFor='username'>Username : </label>
            <input type='email' name='username' onChange={handleUname} required></input>
            <br/><br/>
            <label htmlFor='password'>Password : </label>
            <input type='password' name='password' onChange={handlePass} required></input>
            <br/><br/>
            <button type="submit" onClick={handleSignin}>LogIn</button>
        </form>
        </div>
    );
}
  
  export default Signin;