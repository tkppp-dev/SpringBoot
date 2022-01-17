export default {
  register (detail){
    return new Promise((resolve, reject) => {
      detail.emailAddress === 'test@test.com'
        ? resolve({result: 'success'}) : reject(new Error('이미 등록된 사용자입니다.'))
    })
  }
}