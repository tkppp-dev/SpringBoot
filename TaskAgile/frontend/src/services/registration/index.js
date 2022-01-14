import axios from "axios"

export default {
  register (detail){
    return new Promise(async (resolve, reject) => {
      try{
        const data = await axios.post('/registertions', detail)
        resolve(data)
      }catch(e){
        reject(e)
      }
    })
  }
}