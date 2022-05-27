import './Login.css';
import LoginDesign from "./LoginDesign";
import {useEffect, useState} from "react";
import {useNavigate} from 'react-router';

function Login() {

    useEffect(() => {
        document.title = "Special Department | Login";
        const getUserData = async () => {
            const allUser = await getUsersFromDatabase();
            setGetData(allUser);
        }
        getUserData();
    }, []);

    const navigation = useNavigate();
    const [getData, setGetData] = useState([]);

    const getUsersFromDatabase =
        async () => {
            const response = await fetch(
                "http://localhost:8080/api/getAllUser"
            );
            return await response.json();
        };

    return (
        <div className="container">
            <LoginDesign navigation={navigation} getUserData={getData ? getData : null}/>
        </div>
    )

}

export default Login;