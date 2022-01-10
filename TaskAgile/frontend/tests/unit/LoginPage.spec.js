import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage'

describe('LoginPage.vue', () => {
  it('should render correct contents', () => {
    const vm = shallowMount(LoginPage).vm
    expect(vm.$el.querySelector('h1').textContent)
      .toEqual('TaskAgile')
  })
})
