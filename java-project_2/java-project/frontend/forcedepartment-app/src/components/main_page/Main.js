import Header from '../header/Header';
import Footer from '../footer/Footer';
import Sidebar from '../sidebar/Sidebar';
import WorkerFeed from '../worker_feed/WorkerFeed';
import './Main.css'

import {useState, useEffect } from "react";

const Main = () => {
    const [workers, setWorkers] = useState([]);

    useEffect(() => {
        document.title = "Special Department";
        const getWorkers = async () => {
            const workersByRate = await fetchWorkers();
            setWorkers(workersByRate);
        }
        getWorkers();
    }, [])

    const fetchWorkers = async () => {
        const response = await fetch("http://localhost:8080/api/getWorkersByRating");
        const data = await response.json();
        return data;
    }

    const fetchMenuPoint = async (mainMenuName, menuName) => {
        const response = await fetch(`http://localhost:8080/api/getAllWorkerBy${mainMenuName}/${menuName}`);
        const data = await response.json();
        setWorkers(data);
    }

    const fetchWorkersExtraSearch = async(name, workObject, profession, rate) => {
        const response = await fetch(`http://localhost:8080/api/getWorkerByExtraSearch/${name}/${workObject}/${profession}/${rate}`)
        const data = await response.json();
        setWorkers(data);
    }


    return (
        <div className="grid-container">
            <div className="item1">
                <Header  />
            </div>
            <div className="item2">
                <Sidebar  sideBarHandler={fetchMenuPoint} extraSearch={fetchWorkersExtraSearch}/>
            </div>
            <div className="item3">
                 <WorkerFeed workers={workers} />
            </div>
            <div className="item4 main-page-footer">
                <Footer />
            </div>
        </div>
    )
}


export default Main