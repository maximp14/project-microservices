import { API_HOST } from "../utils/url"

export function createClient(data){
    const url = `${API_HOST}/api/clients`
            const params = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            }

            return fetch(url, params).then(response =>{
                if(response.status >= 200 && response.status < 300){
                    return response.json
                }
                return { code: 404, message: "Something went wrong" }
            }).then(result =>{
                return result
            }).catch(err =>{
                return err
            })
}


export function createAddress(data){
    const url = `${API_HOST}/api/address`
        const params = {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        }

        return fetch(url, params).then(response =>{
            if(response.status >= 200 && response.status < 300){
                return response.json
            }
            return { code: 404, message: "Something went wrong" }
        }).then(result =>{
            return result
        }).catch(err =>{
            return err
        })
}

export function getClientList(){
    const url = `${API_HOST}/api/clients`

    const params = {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },        
    }

    return fetch(url, params).then(response =>{
        if(response.status >= 200 && response.status < 300){
            return response.json
        }
        return { code: 404, message: "Something went wrong" }
    }).then(result =>{
        return result
    }).catch(err =>{
        return err;
    })
}