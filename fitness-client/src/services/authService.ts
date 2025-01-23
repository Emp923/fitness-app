export const login = async (userCredentials: { username: string, password: string }) => {
  try {
    const res = await fetch('/api/login', {
      method: 'POST',
      body: JSON.stringify(userCredentials),
      headers: { 'Content-Type': 'application/json' }
    });
    return res;
  } catch (error) {
    throw error;
  }
};
