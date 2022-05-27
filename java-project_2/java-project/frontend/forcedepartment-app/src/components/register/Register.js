import './Register.css';
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router';
import RegisterForEveryUser from "./RegisterForEveryUser";
import RegisterForWorker from "./RegisterForWorker";

//TODO: sent data to the database, get data from database
function Register() {

    const userTypeData = ["USER", "WORKER"];
    const [userType, setUserType] = useState('');
    const [previousData, setPreviousData] = useState([]);
    const [getDataFromDatabase, setGetDataFromDatabase] = useState([]);
    const emailExist = false;

    useEffect(()=> {
        document.title = "Special Department | Registration";
        const getUserData = async () => {
            const allUser = await getUsersFromDatabase();
            setGetDataFromDatabase(allUser);
        }
        getUserData();
    }, []);

    const navigate = useNavigate();


    const getUsersFromDatabase = async () => {
            const response = await fetch(
                "http://localhost:8080/api/getAllUser"
            );
            return await response.json();
        };

    const userTypeHandler = (user) => {
        setUserType(user)
    }

    const previousDataHandler = (data) => {
        setPreviousData(data);
    }

    const workerDataHandler = (data) => {
        return(data);
    }


    return (
        <div className="container">
            <div className="register-form">
                {userType === "WORKER" ? 
                    <RegisterForWorker previousData={previousData}
                                       workerDataHandler={workerDataHandler}
                                       navigate={navigate}/> :
                    <RegisterForEveryUser userTypeData={userTypeData} previousDataHandler={previousDataHandler}
                                          userTypeHandler={userTypeHandler} navigate={navigate}
                                          getDataFromDatabase={getDataFromDatabase ? getDataFromDatabase: null}
                                          isEmailExist={emailExist}/>}
            </div>
        </div>
    )
}

export default Register