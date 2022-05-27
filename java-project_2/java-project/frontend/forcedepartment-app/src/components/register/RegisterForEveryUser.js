import './Register.css';
import {useEffect, useState} from "react";
import {Link} from 'react-router-dom'

//TODO: sent data to the database, get data from database
function RegisterForEveryUser(props) {

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthOfDate, setBirthOfDate] = useState('');
    const [userType, setUserType] = useState('');
    const [password, setPassword] = useState('');
    const [passwordAgain, setPasswordAgain] = useState('');
    const [email, setEmail] = useState('');
    const [usedEmail, setUsedEmail] = useState(false);
    const [rightPasswords, setRightPasswords] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const navigate = props.navigate;
    const userTypeData = props.userTypeData
    const previousDataHandler = props.previousDataHandler;


    const handleSubmit = (event) => {
        event.preventDefault();
        checkEmailExistsFetch();
    }

    //TODO: Fix UsedEmail hook (first value always false after works normally)
    const checkEmailExistsFetch = async () => {
        const response = await fetch(`http://localhost:8080/api/ifEmailExist/${email}`);
        const data = await response.json();
        setUsedEmail(data);
        checkPassword();
        checkEmailIsExist(data)
    }

    const checkEmailIsExist = (usedEmail) => {

        if (usedEmail) {
            setErrorMessage("The email address is already in use!\n Try again!");
        }
        else {
            setUsedEmail(!usedEmail);
        }
    }

    const checkPassword = () => {
        if (password !== passwordAgain) {
            setErrorMessage("The passwords do not match!\n Try again!");
            setRightPasswords(false);
        } else {
            setRightPasswords(true);
        }
    }


    useEffect(() => {
        const currentData = {firstName, lastName, email, birthOfDate, password, userType};

        const saveData = () => {
            if (userType === 'USER') {
                setErrorMessage('');
                saveDataIntoTheDatabase();
                navigate('/login');
            } else {
                props.userTypeHandler(userType);
                setUserType(userType)
                previousDataHandler(currentData);
            }
        }

        const saveDataIntoTheDatabase = () => {
            fetch('http://localhost:8080/api/getAllUser', {
                method: "POST",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(currentData)
            })
                .then((response) => response.json())
                .then((responseJson) => {
                    console.warn(responseJson);
                })
                .catch((error) => {
                    console.warn(error);
                });
        }

        if (!usedEmail && rightPasswords) {
            saveData();
        }

    }, [usedEmail, rightPasswords, navigate, previousDataHandler, props, userType, birthOfDate, email, firstName, lastName, password])

    return (
        <div className="register-panel">
            <br/>
            <h1>Registration</h1>
            {{errorMessage} ?
                <p id="wrongLogin">{errorMessage}</p> : null}
            <hr/>
            <form onSubmit={handleSubmit}>
                <input type="text" id="firstName" name="firstName" placeholder="First Name"
                       value={firstName}
                       onChange={(e) => setFirstName(e.target.value)}
                       required/>
                <input type="text" id="lastName" name="lastName" placeholder="Last Name"
                       value={lastName}
                       onChange={(e) => setLastName(e.target.value)}
                       required/>
                <input type="email" id="email" name="email" placeholder="Email address"
                       value={email}
                       onChange={(e) => setEmail(e.target.value)}
                       required/>
                <input type="date" id="userBirthOfDate" name="userBirthOfDate" placeholder="Birth of date"
                       value={birthOfDate}
                       onChange={(e) => setBirthOfDate(e.target.value)}
                       required/>
                <div className="password">
                    <input type="password" id="password" name="password" placeholder="Password"
                           value={password}
                           onChange={(e) => setPassword(e.target.value)}
                           required/>
                    <input type="password" id="passwordAgain" name="passwordAgain" placeholder="Password again"
                           value={passwordAgain}
                           onChange={(e) => setPasswordAgain(e.target.value)}
                           required/>
                </div>
                {userTypeData.map((user) =>
                    <span key={user} className="userType">
                            <input type="radio" value={user} id={user} name="groupType"
                                   onChange={(e) => setUserType(e.target.value)}
                                   required/> {user}
                        </span>
                )}
                <button type="submit" value="Submit">Submit</button>
            </form>
            <Link to={"/"}>
                <button type="button" value="Main page">Main page</button>
            </Link>
        </div>
    )


}

export default RegisterForEveryUser;