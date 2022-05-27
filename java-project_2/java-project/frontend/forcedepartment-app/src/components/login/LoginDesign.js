import {useContext, useEffect, useState} from "react";
import {Link} from 'react-router-dom'
import {UserContext} from './UserContext'

function LoginDesign(props) {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [userExist, setUserExist] = useState(false);
    const user = useContext(UserContext);
    const navigation = props.navigation;

    useEffect(() => {
    },[userExist])

    const handleSubmit = (e) => {
        e.preventDefault();
        checkIsUserExistFetch();
    }

    //TODO: Fix UsedEmail hook (first value always false after works normally)
    const checkIsUserExistFetch = async () => {
        const response = await fetch(`http://localhost:8080/api/checkUserIsExist/${email}:${password}`);
        const data = await response.json();
        if (data) {
            user.toggleUserState();
        }
        setUserExist(data);
        errorMessageSetter(data);
    }

    const errorMessageSetter = (userExist) => {
            if (!userExist) {
                setErrorMessage("Wrong email address or password was given! Try again..")
            } else if (userExist) {
                navigation("/");
            }
    }

    return (
        <div className="login-block">
            <h1>Login</h1>
            {errorMessage ?
                <p id="errorMessage">{errorMessage}</p> : null}
            <form onSubmit={handleSubmit}>
                <input type="email" id="email" name="email" placeholder="Email address"
                       onChange={(e) => setEmail(e.target.value)}
                       required/>
                <input type="password" id="password" name="password" placeholder="Password"
                       onChange={(e) => setPassword(e.target.value)}
                       required/>
                <button type="submit">Submit</button>
            </form>

            <div className="mainPageHref">
                <Link to={"/"}>
                    <button type="button">Main page</button>
                </Link>
                <p>If you don't have an account, press down bellow to register</p>
                <Link to={"/registration"}>
                    <button type="button">Registration</button>
                </Link>
            </div>
        </div>
    )
}


export default LoginDesign;