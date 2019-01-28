import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import cashier from '@/components/cashier'
import flow from '@/components/flow'
import phonebill from '@/components/phonebill'

Vue.use(Router)

// noinspection JSAnnotator
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'flow',
      component: flow
    },
    { path: '/cashier',
      component: cashier
    },
    { path: '/flow',
      component: flow
    },
    { path: '/phonebill',
      component: phonebill
    }
  ]
})
