FROM python:3.13.9
ENV PORT=8080
EXPOSE 8080
WORKDIR /usr/src/app
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
ENTRYPOINT ["python"]
CMD ["app.py"]