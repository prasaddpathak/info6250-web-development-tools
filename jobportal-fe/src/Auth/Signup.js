import { useEffect, useState } from "react";

function Signup() {

    const [newUser, setNewUser] = useState({
        first_name : '',
        last_name : '',
        age : 0,
        email_id : '',
        password : '',
        user_type : ''
    })

    const handleSignup = async () => {

        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newUser)
        }

        const newUserResponse = await fetch('http://localhost:8080/v1/users', requestOptions)

        alert(`${newUserResponse.status}`)

    }

    const handleChange = (e) => {
        const value =  e.target.value;
        const name = e.target.name;

        setNewUser({
            ...newUser,
            [name]: value
        });

    }

    return (
        <div className="Login">
        <h1>Create a new account</h1>
        <form>
            <br/>
            <label htmlFor='first_name'>First Name : </label>
            <input type='text' name='first_name' required onChange={handleChange}></input>
            <br/><br/>
            <label htmlFor='last_name'>Last Name : </label>
            <input type='text' name='last_name' required onChange={handleChange}></input>
            <br/><br/>
            <label htmlFor='age'>Age : </label>
            <input type='number' name='age' required onChange={handleChange}></input>
            <br/><br/>
            <label htmlFor='email_id'>Email Id : </label>
            <input type='email' name='email_id' required onChange={handleChange}></input>
            <br/><br/>
            <label htmlFor='password'>Password : </label>
            <input type='password' name='password' required onChange={handleChange}></input>
            <br/><br/>
            <label htmlFor='user_type'>Profile Type : </label>
            <select name='user_type' id="user_type" onChange={handleChange}>
                <option value='STUDENT'>Student</option>
                <option value='EMPLOYER'>Employer</option>
            </select>
            <br/><br/>
            <button onClick={handleSignup}>SignUp</button>
        </form>
        </div>
    );
}
  
  export default Signup;