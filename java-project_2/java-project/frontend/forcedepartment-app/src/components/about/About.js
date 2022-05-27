import {useEffect} from "react";

const About = () => {

    useEffect(()=> {
        document.title = "Special Department | About us";
    },[]);

    return (
        <div>
            <p>This is the special department</p>
        </div>
    )
}

export default About
