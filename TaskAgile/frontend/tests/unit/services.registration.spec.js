import axios from 'axios';
import registrationService from '@/services/registration';

jest.mock('axios');

describe('services/registration', () => {
  beforeEach(() => {});

  afterEach(() => {
    jest.restoreAllMocks();
  });

  it('요청이 성공하면, 성공 응답이 호출자에게 전달되어야 한다.', async () => {
    axios.post.mockImplementation(() =>
      Promise.resolve({
        status: 200,
        data: {
          result: 'success',
        },
      })
    );

    const response = await registrationService.register();
    expect(response.data.result).toEqual('success');
  });

  it('요청이 실패하면, 실패 응답이 호출자에게 전달되어야 한다.', async () => {
    axios.post.mockImplementation(() =>
      Promise.reject({
        status: 400,
        data: {
          message: 'Bad request',
        },
      })
    );
    try{
      await registrationService.register();
    }catch(e){
      expect(e.data.message).toEqual('Bad request');
    }
  });
});
