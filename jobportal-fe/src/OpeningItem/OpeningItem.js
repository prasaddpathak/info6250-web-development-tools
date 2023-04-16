import './OpeningItem.scss'
import axios from 'axios';


function OpeningItem(props) {


    const handleApply = async () => {
        // console.log(`User - ${props.user.id} Applied for - ${props.data.id}`);

        const applyURL = 'http://localhost:8080/v1/applications/' + props.user.id
        const applyPayload = {
            opening_id : props.data.id,
            status : "APPLIED",
            applied_on : Date.now()
        }

        console.log(applyURL);
        console.log(applyPayload);
        try {
            const response = await axios.post(applyURL, applyPayload)
            alert(response.data);
            props.fetchAppliedOpenings()
        } catch (e) {
            alert(e.response.data)
        }
        
    }

    return (
      <div className="OpeningItem">
        <div>
            {props.data.company}
        </div>
        <div>
            {props.data.position}
        </div>
        <div>
            {props.data.location}
        </div>
        <div>
            {props.data.requirements}
        </div>
        <div>
            ${props.data.salary}
        </div>
        {
            !props.hasApplied &&
                <div>
                    <button onClick={handleApply}>Apply</button>
                </div>
        }
        
      </div>
    );
  }
  
  export default OpeningItem;