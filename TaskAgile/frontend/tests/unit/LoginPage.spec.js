import { shallowMount } from '@vue/test-utils';
import LoginPage from '@/views/LoginPage';

describe('LoginPage.vue', () => {
  const wrapper = shallowMount(LoginPage);
  it('should render correct contents', () => {
    expect(wrapper.find('h1').text()).toEqual('TaskAgile');
  });
});
