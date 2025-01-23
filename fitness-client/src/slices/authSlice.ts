import { createSlice } from "@reduxjs/toolkit";

const getInitialState = (): { userInfo: string | null } => {
  const userInfo = localStorage.getItem('userInfo');
  if (userInfo) {
    return { userInfo };
  }
  return { userInfo: null };
};

const authSlice = createSlice({
  name: 'auth',
  initialState: getInitialState(),
  reducers: {
    setCredentials: (state, action) => {
      state.userInfo = JSON.stringify(action.payload);
      localStorage.setItem("userInfo", JSON.stringify(action.payload));
    },
    logout: (state) => {
      state.userInfo = null;
      localStorage.removeItem("userInfo");
    },
  },
});

export const { setCredentials, logout } = authSlice.actions;

export default authSlice.reducer;
