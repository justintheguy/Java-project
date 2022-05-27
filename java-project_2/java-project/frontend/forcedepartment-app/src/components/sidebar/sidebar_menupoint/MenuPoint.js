
const MenuPoint = (props) => {
    return (
        <>
        <div className="mainMenuPoint" onClick={props.listMenuPoint}>{props.menuTitle}</div>
        {props.showMenuPoint && 
            <ul>
                {props.allMenuPoints.map((menuPoint) => (
                    <li key={menuPoint}
                        onClick={() => {props.sideBarHandler(props.fetchRoutePart, menuPoint)}} 
                        className="menuPoint">
                        {menuPoint}
                    </li>))}
            </ul>
        }
        </>
    )
}


export default MenuPoint            