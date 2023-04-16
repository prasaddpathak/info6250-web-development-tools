import { useState, useEffect } from 'react';
import axios from 'axios';
import './Student.scss'
import OpeningItem from '../../OpeningItem/OpeningItem';
import AppliedItem from '../../AppliedItem/AppliedItem';

function Student(props) {
    
    const [openings, setOpenings] = useState([{}])
    const [appliedData, setAppliedData] = useState([{}])
    const [appliedOpeningsData, setAppliedOpeningsData] = useState([{}])
    const [mergedData, setMergedData] = useState([{}])
    
    const fetchOpenings = async () => {
        await axios.get('http://localhost:8080/v1/openings')
            .then(response => {
                setOpenings(response.data)
            })
    }

    const fetchAppliedOpenings = async () => {
        const userUrl = 'http://localhost:8080/v1/applications/'+props.user.id
        const response = await axios.get(userUrl)
        
        setAppliedData(response.data);
        // let ids = []
        // response.data.map(aopen => ids.push(aopen.opening_id) )
        // const openingUrl = 'http://localhost:8080/v1/openings/'
        // const res = await Promise.all(ids.map(id => axios.get(openingUrl + id)))
        // const data = res.map((r) => r.data);

        // // console.log(data.flat());
        // setAppliedOpeningsData(data.flat())
    }

    const extractOpeningData = async () => {
        let ids = []
        // console.log('Applied Data ---------');
        // console.log(appliedData);
        appliedData && appliedData.map(aopen => ids.push(aopen.opening_id) )
        const openingUrl = 'http://localhost:8080/v1/openings/id/'
        const res = await Promise.all(ids.map(id => axios.get(openingUrl + id)))
        const data = res.map((r) => r.data);
        // console.log(data.flat());
        setAppliedOpeningsData(data.flat())
    }

    const mergeAppliedData = () => {
        // console.log('Merged Data -----------');

        // console.log(appliedData);
        // console.log(appliedOpeningsData);
        let mergedData = new Array()
        for(let o of appliedOpeningsData) {
            for (let a of appliedData) {
                if (a.opening_id === o.id) {
                    mergedData.push({...o, ...a})
                }
            }
        }
        // console.log(mergedData);
        setMergedData(mergedData)
    }

    // useEffect(() => {
    //     fetchOpenings();
    //     fetchAppliedOpenings();
    //     extractOpeningData();
    //     mergeAppliedData();
    // }, [])
    
    useEffect(() => {
        fetchOpenings();
        // fetchAppliedOpenings();
        // extractOpeningData();
        // mergeAppliedData();
    }, [])

    useEffect(() => {
        // fetchOpenings();
        fetchAppliedOpenings();
        // extractOpeningData();
        // mergeAppliedData();

    }, [openings])

    useEffect(() => {
        // fetchOpenings();
        // fetchAppliedOpenings();
        extractOpeningData();
        // mergeAppliedData();

    }, [appliedData])

    useEffect(() => {
        // fetchOpenings();
        // fetchAppliedOpenings();
        // extractOpeningData();

        mergeAppliedData();
    }, [appliedOpeningsData])

    return (
      <div className="Student">
        <h3>Current Openings</h3>
        { 
            openings && openings.map(opening => <OpeningItem data={opening} user={props.user} fetchAppliedOpenings={fetchAppliedOpenings}/> )                
        }
        <h3>Positions Applied to</h3>
        {
            mergedData && mergedData.map(opening => <AppliedItem data={opening} user={props.user} /> )
        }
      </div>
    );
  }
  
  export default Student;
  