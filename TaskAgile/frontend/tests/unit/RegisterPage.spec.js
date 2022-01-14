import { mount } from '@vue/test-utils';
import RegisterPage from '@/views/RegisterPage';

jest.mock('@/services/registration');

describe('RegisterPage.vue', () => {
  let wrapper, fieldUsername, fieldPassword, fieldEmailAddress, buttonSubmit;
  const mockRouter = {
    push: jest.fn(),
  };

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      global: {
        mocks: {
          $router: mockRouter,
        },
        /* or
        plugins: [router]
        */
      },
    });
    fieldUsername = wrapper.find('#username');
    fieldEmailAddress = wrapper.find('#emailAddress');
    fieldPassword = wrapper.find('#password');
    buttonSubmit = wrapper.find('form button[type="submit"]');
  });

  afterAll(() => {
    jest.restoreAllMocks();
  });

  it('마운트 시, 컨텐츠가 올바르게 렌더링 되어야 한다', () => {
    expect(wrapper.find('.logo').attributes('src')).toEqual(
      '/static/images/logo.png'
    );

    expect(wrapper.find('.tagline').text()).toEqual(
      'Open source task management tool'
    );

    expect(fieldUsername.element.value).toEqual('');
    expect(fieldEmailAddress.element.value).toEqual('');
    expect(fieldPassword.element.value).toEqual('');
    expect(buttonSubmit.text()).toEqual('Create account');
  });

  it('데이터 모델의 초기값이 빈 문자열이어야 한다.', () => {
    expect(wrapper.vm.form.username).toEqual('');
    expect(wrapper.vm.form.emailAddress).toEqual('');
    expect(wrapper.vm.form.password).toEqual('');
  });

  it('폼의 입력과 데이터 모델의 바인딩이 정상적으로 되야 한다.', async () => {
    const form = wrapper.vm.form;
    const username = 'tkppp';
    const emailAddress = 'gowldla0423@naver.com';
    const password = 'password1';

    await fieldUsername.setValue(username);
    await fieldEmailAddress.setValue(emailAddress);
    await fieldPassword.setValue(password);

    expect(fieldUsername.element.value).toEqual(form.username);
    expect(fieldEmailAddress.element.value).toEqual(form.emailAddress);
    expect(fieldPassword.element.value).toEqual(form.password);
  });

  it('폼 제출 이벤트 핸들러에 "submitForm"이 등록되어야 한다.', async () => {
    const spyFn = jest.spyOn(wrapper.vm, 'submitForm')

    await buttonSubmit.trigger('submit');
    expect(spyFn).toBeCalled();
  });

  it('새로운 유저의 경우, 회원가입이 성공해야 한다', async () => {
    wrapper.vm.form.username = 'tkppp';
    wrapper.vm.form.emailAddress = 'gowldla0423@naver.com';
    wrapper.vm.form.password = 'password1';
    await buttonSubmit.trigger('submit')
    expect(mockRouter.push).toHaveBeenCalledWith({ name: 'LoginPage' });
    
  });

  it('등록된 유저의 경우, 회원가입이 실패하고 실패 메세지를 표시해야 한다.', async () => {
    wrapper.vm.form.emailAddress = 'exist@local';
    expect(wrapper.find('.failed').isVisible()).toBe(false);
    await buttonSubmit.trigger('submit')
    expect(wrapper.find('.failed').isVisible()).toBe(true);
  });
});
