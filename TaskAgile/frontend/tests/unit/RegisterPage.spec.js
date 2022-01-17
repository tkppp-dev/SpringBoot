import { mount } from '@vue/test-utils';
import RegisterPage from '@/views/RegisterPage';
import registrationService from '@/services/registration'

jest.mock('@/services/registration');

describe('RegisterPage.vue', () => {
  let wrapper, fieldUsername, fieldPassword, fieldEmailAddress, buttonSubmit;
  let registerSpy;
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

    registerSpy = jest.spyOn(registrationService, 'register');
  });

  afterAll(() => {
    jest.restoreAllMocks();
    registerSpy.mockReset();
    registerSpy.mockRestore();
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
    expect(buttonSubmit.text()).toEqual('계정 생성');
  });

  it('데이터 모델 폼의 초기값이 빈 문자열이어야 한다.', () => {
    expect(wrapper.vm.form.username).toEqual('');
    expect(wrapper.vm.form.emailAddress).toEqual('');
    expect(wrapper.vm.form.password).toEqual('');
  });

  it('데이터모델 validationChecker가 정상적으로 초기화되어야한다.', async () => {
    expect(wrapper.vm.validationChecker).toBeDefined()
  })

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
    await fieldUsername.setValue('testName');
    await fieldEmailAddress.setValue('test@test.com');
    await fieldPassword.setValue('password');
    const spyFn = jest.spyOn(wrapper.vm, 'submitForm');

    await buttonSubmit.trigger('submit');
    expect(spyFn).toBeCalled();
  });

  it('새로운 유저의 경우, 회원가입이 성공해야 한다', async () => {
    await fieldUsername.setValue('testName');
    await fieldEmailAddress.setValue('test@test.com');
    await fieldPassword.setValue('password');
    await wrapper.vm.submitForm()
    expect(mockRouter.push).toHaveBeenCalledWith({ name: 'LoginPage' });
  });


  it('등록된 유저의 경우, 회원가입이 실패하고 실패 메세지를 표시해야 한다.', async () => {
    await fieldUsername.setValue('testName');
    await fieldPassword.setValue('password');
    await fieldEmailAddress.setValue('exist@local');

    expect(wrapper.find('.failed').isVisible()).toBe(false);

    await wrapper.vm.submitForm()
    expect(wrapper.find('.failed').text()).toEqual('회원가입에 실패했습니다. 사유 : 이미 등록된 사용자입니다.')
    expect(wrapper.find('.failed').isVisible()).toBe(true);
  });

  describe('폼 입력 검증 테스트', () => {
    it('빈 항목이 하나라도 존재할 경우, 제출 버튼이 비활성화 되어야 한다.', async () => {
      await fieldUsername.setValue('');
      await fieldEmailAddress.setValue('test@test.com');
      await fieldPassword.setValue('password');
  
      expect(wrapper.vm.isFormValid).toBe(false)
    })

    it('사용자 이름은 2자 이상, 영한과 숫자로 이루어져야한다.', async () => {
      await fieldUsername.setValue('');
      expect(wrapper.vm.validationChecker.username.invalid).toBe(true)

      await fieldUsername.setValue('a');
      expect(wrapper.vm.usernameValidationError).toEqual('사용자 이름은 2자 이상이어야 합니다.')
  
      await fieldUsername.setValue('@#$')
      expect(wrapper.vm.usernameValidationError).toEqual('사용자 이름은 한글, 영문자, 숫자로만 이루어져야 합니다.')
  
      await fieldUsername.setValue('tkppp')
      expect(wrapper.vm.validationChecker.username.invalid).toBe(false)
    })
  
    it('이메일 주소는 형식이 test@test.com 과 같은 형식이어야 한다.', async () => {
      await fieldEmailAddress.setValue('test')
      expect(wrapper.vm.emailValidationError).toEqual('이메일 형식이 올바르지 않습니다.')
  
      await fieldEmailAddress.setValue('test@test.com')
      expect(wrapper.vm.validationChecker.emailAddress.invalid).toBe(false)
    })
  
    it('비밀번호는 6자 이상이어야 한다.', async () => {
      await fieldPassword.setValue('pwd')
      expect(wrapper.vm.passwordValidationError).toEqual('비밀번호는 6자 이상이어야 합니다.')
  
      await fieldPassword.setValue('password')
      expect(wrapper.vm.validationChecker.password.invalid).toBe(false)
    })
  })
});
