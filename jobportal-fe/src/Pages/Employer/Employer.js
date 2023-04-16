import './Employer.scss'
import { useState, useEffect } from 'react'
import axios from 'axios';
import ExistingOpeningItem from '../../ExistingOpeningItem/ExistingOpeningItem';

function Employer(props) {

  const [newOpening, setNewOpening] = useState({
    company : '',
    position : '',
    location : '',
    description : '',
    requirements : '',
    salary : 0,
    open : true,
  })
  const [existingOpenings, setExistingOpenings] = useState([{}])

  const handleSignup = async () => {

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newOpening)
    }

    const newUserResponse = await fetch('http://localhost:8080/v1/openings/' + props.user.id, requestOptions)

    alert(`${newUserResponse.status}`)

  }

  const handleChange = (e) => {
    const value =  e.target.value;
    const name = e.target.name;

    setNewOpening({
        ...newOpening,
        [name]: value
    });

  }

  const fetchOpeningsForEmployer = async () => {
    const openingUrl = 'http://localhost:8080/v1/openings/'+props.user.id
    const response = await axios.get(openingUrl)
    setExistingOpenings(response.data);
  }

  useEffect(() => {
    fetchOpeningsForEmployer()
  }, [])
  
  return (
    <div className="Employer">
      <h3>Create a new Opening</h3>
      <form>
        <br/>
        <label htmlFor='company'>Company Name : </label>
        <input type='text' name='company' required onChange={handleChange}></input>
        <br/><br/>
        <label htmlFor='position'>Position : </label>
        <input type='text' name='position' required onChange={handleChange}></input>
        <br/><br/>
        <label htmlFor='location'>Location : </label>
        <input type='text' name='location' required onChange={handleChange}></input>
        <br/><br/>
        <label htmlFor='description'>Description : </label>
        <input type='text' name='description' required onChange={handleChange}></input>
        <br/><br/>
        <label htmlFor='requirements'>Requirements : </label>
        <input type='text' name='requirements' required onChange={handleChange}></input>
        <br/><br/>
        <label htmlFor='salary'>Salary : </label>
        <input type='number' name='salary' required onChange={handleChange}></input>
        <br/><br/>
        <button onClick={handleSignup}>Create</button>

      </form>
      <h3>Existing Openings</h3>
      {
        existingOpenings && existingOpenings.map(opening => <ExistingOpeningItem data={opening} />)
      }
    </div>
  );
}
  
  export default Employer;