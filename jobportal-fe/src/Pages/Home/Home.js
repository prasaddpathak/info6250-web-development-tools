import { useState, useEffect } from "react";
import Auth from "../../Auth/Auth";
import Employer from "../Employer/Employer";
import Student from "../Student/Student";

function Home() {

    const [loggedUser, setLoggedUser] = useState({})

    // useEffect(() => {
    //     console.log(loggedUser);
    // }, [loggedUser])
    
    const handleLogout = () => {
        setLoggedUser({})
    }

    return (
        <div className="Home">
        { loggedUser.email_id 
            ? 
                <>
                    <h1>Welcome {loggedUser.first_name}</h1>
                    <h3>You are a {loggedUser.user_type}</h3>
                    <span onClick={handleLogout} className='link'>Logout</span>
                    {loggedUser.user_type === "STUDENT" 
                        ?
                            <Student user={loggedUser}/> 
                        :  
                            <Employer user={loggedUser}/>
                    } 
                </>
            : 
                <Auth setLoggedUser = {setLoggedUser} />}
            </div>
    );
}

export default Home;