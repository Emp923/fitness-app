export const login = async (userCredentials: { username: string, password: string }) => {
  const res = await fetch('/api/login', {
    method: 'POST',
    body: JSON.stringify(userCredentials),
    headers: { 'Content-Type': 'application/json' }
  });
  return res.json();
};
