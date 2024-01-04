import {default as api} from '../../utils/api'

const defs = {
  state: () => ({
    defsList: [],
  }),
  mutations: {
    updateDef(state){
      GetDefs()
    }
  },
  getters: {
    defType:state=>{
      return state.defsList.filter(item=>item.defType==1)
    },
    devType: state => {
      return state.defsList.filter(item => item.defType == 2)
    },
    devStatus: state => {
      return state.defsList.filter(item => item.defType == 3)
    },
    partType: state => {
      return state.defsList.filter(item => item.defType == 4)
    },
    partStatus: state => {
      return state.defsList.filter(item => item.defType == 5)
    },
    eventStage: state => {
      return state.defsList.filter(item => item.defType == 6)
    },
    eventType: state => {
      return state.defsList.filter(item => item.defType == 60)
    },
  },
  actions: {
    GetDefs() {
      return new Promise((resolve, reject) => {
        api({
          url: '/defs/list',
          method: 'get'
        })
          .then(data => {
            this.state.defs.defsList = data.list
            this.state.defsList=data.list
            if (resolve)
              resolve(data)
          }
          ).catch(error => {
            reject(error)
          }
          )
      })

    }
  }}

export default defs
