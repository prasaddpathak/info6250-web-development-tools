import './AppliedItem.scss'

function AppliedItem(props) {

    return (
      <div className="AppliedItem">
        <div>
            {props.data.applied_on && props.data.applied_on.split("T")[0]}
            {/* {props.data.applied_on} */}
        </div>
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
        <div>
            {props.data.status}
        </div>
        
      </div>
    );
  }
  
  export default AppliedItem;