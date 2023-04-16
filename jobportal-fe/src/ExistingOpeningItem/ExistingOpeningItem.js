import axios from 'axios';
import { useEffect, useState } from 'react';
import './ExistingOpeningItem.scss'

function ExistingOpeningItem(props) {

    const [appliedUsers, setAppliedUsers] = useState([{}])

    const fetchApplicationsForOpening = async () => {
        const response = await axios.get('http://localhost:8080/v1/applications')
        const apps = response.data
        const appsForOpenings = apps.filter(app => app.opening_id === props.data.id)
        setAppliedUsers(appsForOpenings)
        
    }

    const handleAccept = async (user_id, opening_id) =>{
        const updatePayload = {
            opening_id : opening_id,
            status : "ACCEPTED"
        }
        const updateURL = 'http://localhost:8080/v1/applications/' + user_id
        const response = await axios.put(updateURL, updatePayload)
        console.log(response.data)    
        fetchApplicationsForOpening()  

    }

    const handleReject = async (user_id, opening_id) => {
        const updatePayload = {
            opening_id : opening_id,
            status : "REJECTED"
        }
        const updateURL = 'http://localhost:8080/v1/applications/' + user_id
        const response = await axios.put(updateURL, updatePayload)
        console.log(response.data)
        fetchApplicationsForOpening()
    }

    useEffect(() => {
        fetchApplicationsForOpening()
    }, [])

    return (
        <div className="ExistingOpeningContainer">
            <div className="ExistingOpening">
                <div>{props.data.company}</div>
                <div>{props.data.position}</div>
                <div>{props.data.location}</div>
                <div>{props.data.requirements}</div>
                <div>${props.data.salary}</div>
                <div>{props.data.open ? "Open" : "Closed"}</div>
            </div>
            <div className='AppliedUsers'>
                {appliedUsers && appliedUsers.map(ao => 
                    <div className='card'>
                        <div>{ao.applied_on && ao.applied_on.split("T")[0]}</div>
                        <div>{ao.user_id}</div>
                        <div>{ao.status}</div>
                        <div><button onClick={() => handleAccept(ao.user_id, ao.opening_id)}>Accept</button></div>
                        <div><button onClick={() => handleReject(ao.user_id, ao.opening_id)}>Reject</button></div>
                    </div>
                )}
            </div>
        </div>
    );
  }
  
  export default ExistingOpeningItem;