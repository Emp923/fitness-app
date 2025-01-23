export const login = async (userCredentials: { username: string, password: string }) => {
  try {
    const res = await fetch("/api/login", {
      method: "POST",
      body: JSON.stringify(userCredentials),
      headers: { "Content-Type": "application/json" }
    });
    return res;
  } catch (error) {
    throw error;
  }
};

export const register = async (newUserCredentials: {
  username: string,
  password: string,
  confirmPassword: string,
  role?: string
}) => {
  newUserCredentials.role = "ROLE_USER";
  try {
    const res = await fetch("/api/register", {
      method: "POST",
      body: JSON.stringify(newUserCredentials),
      headers: { "Content-Type": "application/json" }
    });
    return res;
  } catch (error) {
    throw error;
  }
};
