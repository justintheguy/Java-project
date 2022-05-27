import profilePic from '../../img/profile-icon-empty.png';
import { Link } from 'react-router-dom';
    
const WorkerCard = (props) => {
    return (
        <Link className="worker-card-link" to={`/profile/${props.worker.userId}`}>
            <div className="worker-card">
                <div  className="worker-detail">
                    <img src={profilePic} alt={props.worker.imageName}></img><br/>
                    <h3>{`${props.worker.firstName} ${props.worker.lastName} (${props.worker.age})`}</h3><br />
                    <h4>Professions(s):</h4><br />
                    <span className="professions">
                        {props.worker.profession.map((profession, i, arr) => {
                            if (arr.length - 1 === i) { return <span key={profession}>{profession}</span>} 
                            else { return <span key={profession}>{`${profession}, `}</span> }
                        })}
                    </span>
                </div>
            </div>
        </Link>
    )
}


export default WorkerCard