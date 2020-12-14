const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  
  dynastyData: state => state.dynasty.data,
  dynastyList: state => state.dynasty.list,
}
export default getters
