import './Header.css';
import WorkerCard from "./worker_card/WorkerCard";


const WorkerFeed = (props) => {
    return (
        <div>
            {props.workers.length === 0 ? 
                <div > <p id="no-worker-found">No worker found!</p> </div> : 
                <div id="worker-card-container">
                    {props.workers.map((worker) => (
                    <WorkerCard key={worker.userId} worker={worker} />))} 
                </div>
            }    
        </div>

    )
}


export default WorkerFeed