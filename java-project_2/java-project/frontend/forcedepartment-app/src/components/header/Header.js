import './Header.css';
import { Link } from "react-router-dom";

function Header(props) {
    return (
        <div className="nav">
            <div className="logo">
                <Link to="/">{props.title}</Link>
            </div>
            <ul className="navBar">
                <Link className="navbar-element" to="/about_us">About Us</Link>
                <Link className="navbar-element" to="/login">Login</Link>
            </ul>
        </div>
    )
}

Header.defaultProps = {
    title: "Special Department",
}

export default Header
