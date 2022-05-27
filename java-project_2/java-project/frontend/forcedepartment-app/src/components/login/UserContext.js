import React, {useCallback, useState} from 'react'

export const UserContext = React.createContext(null);


function UserProvider(props) {
    const children = props.children;
    const [userState, setUserState] = useState(false);

    const toggleUserState = useCallback(() => {
        setUserState(!userState);
    }, [userState])

    return (
        <UserContext.Provider value={{userState, toggleUserState}}>
            {children}
        </UserContext.Provider>
    )

}

export default UserProvider